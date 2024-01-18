package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_details", adminConfig.getCompanyDetails());
        context.setVariable("welcome_message", "Witaj na stronie!");
        context.setVariable("goodbye_message", "Do zobaczenia!");
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
    public String buildDailyTaskSummaryEmail(String dailyTaskSummaryMessage, int tasksAvailable) {
        Context context = new Context();
        context.setVariable("daily_task_summary_message", dailyTaskSummaryMessage);
        context.setVariable("tasks_available", tasksAvailable);
        context.setVariable("company_details", adminConfig.getCompanyDetails());
        return templateEngine.process("mail/daily-task-summary-mail", context);
    }

}
