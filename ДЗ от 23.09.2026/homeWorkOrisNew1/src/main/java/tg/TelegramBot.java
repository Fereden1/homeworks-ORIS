package tg;

import dao.impl.userDaoImpl;
import dao.userCRUD;
import entities.User;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.List;

public class TelegramBot implements LongPollingUpdateConsumer {

    public static final String BOT_USERNAME = "MyMessengerBotOris_bot";

    private static final String BOT_TOKEN = "8953742444:AAF3-F4TFkT50J9-aKCrkvojCOt217uNN7I";

    private static boolean started = false;

    private final userCRUD userDao = userDaoImpl.getInstance();

    private final TelegramClient telegramClient = new OkHttpTelegramClient(BOT_TOKEN);

    @Override
    public void consume(List<Update> updates) {
        for (Update update : updates) {
            if (!update.hasMessage() || !update.getMessage().hasText()) {
                continue;
            }

            String text = update.getMessage().getText().trim();
            long chatId = update.getMessage().getChatId();

            if (text.startsWith("/start")) {
                String[] parts = text.split(" ");
                if (parts.length < 2) {
                    send(chatId, "отправь код подтверждения");
                    continue;
                }
                confirm(parts[1], chatId);
                continue;
            }
            if (text.matches("\\d{6}")) {
                confirm(text, chatId);
                continue;
            }
            send(chatId, "отправьте код подтверждения");
        }
    }

    private void confirm(String code, long chatId) {
        User user = userDao.findByConfirmationCode(code);

        if (user == null) {
            send(chatId, "неверный код");
            return;
        }
        if (userDao.existsByTelegramChatId(chatId)) {
            send(chatId, "этот тг уже привязан к другому аккаунту");
            return;
        }
        userDao.confirmUser(user.getId(), chatId);
        send(chatId, "аккаунт " + user.getLogin() + " подтверждён. Можно входить на сайт");
    }

    private void send(long chatId, String text) {
        try {
            telegramClient.execute(new SendMessage(String.valueOf(chatId), text));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized void startBot() {
        if (started) {
            return;
        }

        try {
            TelegramBotsLongPollingApplication bots = new TelegramBotsLongPollingApplication();
            bots.registerBot(BOT_TOKEN, new TelegramBot());
            started = true;
            System.out.println("тг бот запущен");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
