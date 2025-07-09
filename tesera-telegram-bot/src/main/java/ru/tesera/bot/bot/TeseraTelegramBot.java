package ru.tesera.bot.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import jakarta.annotation.PostConstruct;
import java.util.List;

@Component
public class TeseraTelegramBot extends TelegramLongPollingBot {

    private final String botToken;
    private final String botUsername;

    public TeseraTelegramBot(
        @Value("${telegram.bot.token}") String botToken,
        @Value("${telegram.bot.username}") String botUsername
    ) {
        super(botToken);
        this.botToken = botToken;
        this.botUsername = botUsername;
    }

    @PostConstruct
    public void setBotCommands() {
        try {
            this.execute(new SetMyCommands(
                List.of(
                    new BotCommand("start", "Запуск бота"),
                    new BotCommand("addCollection", "Добавить коллекцию Tesera"),
                    new BotCommand("collections", "Список коллекций"),
                    new BotCommand("findGame", "Поиск игры"),
                    new BotCommand("createMeeting", "Создать встречу"),
                    new BotCommand("subscribe", "Подписка на игру"),
                    new BotCommand("subscribeDay", "Подписка на день недели")
                ),
                new BotCommandScopeDefault(),
                null
            ));
        } catch (TelegramApiException e) {
            // Логирование ошибки
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            if (messageText.startsWith("/")) {
                String[] parts = messageText.split(" ", 2);
                String command = parts[0].substring(1); // без /
                String args = parts.length > 1 ? parts[1] : "";

                switch (command) {
                    case "start" -> handleStart(chatId);
                    case "addCollection" -> handleAddCollection(chatId, args);
                    // TODO: добавить обработку других команд
                    default -> sendMessage(chatId, "Неизвестная команда. Используйте /start для справки.");
                }
            }
        }
    }

    private void handleStart(Long chatId) {
        String text = "Привет! Я бот для планирования настольных встреч и работы с коллекциями Tesera.\n" +
                "Доступные команды:\n" +
                "/addCollection <ник_на_tesera> — добавить свою коллекцию игр\n" +
                "/collections — список всех коллекций\n" +
                "/findGame <название> — поиск игры среди участников\n" +
                "/createMeeting <дата_время> <макс_участников> <описание> — создать встречу\n" +
                "/subscribe <игра> — подписка на игру\n" +
                "/subscribeDay <день_недели> — подписка на день недели";
        sendMessage(chatId, text);
    }

    private void handleAddCollection(Long chatId, String args) {
        if (args.isBlank()) {
            sendMessage(chatId, "Пожалуйста, укажите ник на Tesera: /addCollection <ник_на_tesera>");
            return;
        }
        // TODO: реализовать добавление коллекции через Tesera API
        sendMessage(chatId, "Добавление коллекции для ника: " + args + " (функция в разработке)");
    }

    private void sendMessage(Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
} 