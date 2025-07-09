package ru.tesera.bot.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
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
        // Здесь будет обработка всех входящих апдейтов (команды, сообщения и т.д.)
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