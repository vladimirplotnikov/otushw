package otus.demo;

import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import otus.core.repository.DataTemplateHibernate;
import otus.core.repository.HibernateUtils;
import otus.core.sessionmanager.TransactionManagerHibernate;
import otus.crm.dbmigrations.MigrationsExecutorFlyway;
import otus.crm.model.*;
import otus.crm.service.DbServiceClientImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DbServiceDemo {

    private static final Logger log = LoggerFactory.getLogger(DbServiceDemo.class);

    public static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";

    public static void main(String[] args) {
        var configuration = new Configuration().configure(HIBERNATE_CFG_FILE);

        var dbUrl = configuration.getProperty("hibernate.connection.url");
        var dbUserName = configuration.getProperty("hibernate.connection.username");
        var dbPassword = configuration.getProperty("hibernate.connection.password");

        new MigrationsExecutorFlyway(dbUrl, dbUserName, dbPassword).executeMigrations();

        var sessionFactory = HibernateUtils.buildSessionFactory(configuration, Client.class, Address.class,Phone.class);

        var transactionManager = new TransactionManagerHibernate(sessionFactory);
///
        var clientTemplate = new DataTemplateHibernate<>(Client.class);
///
        var dbServiceClient = new DbServiceClientImpl(transactionManager, clientTemplate);

        List<Client> clientsList = new ArrayList<>();

        for (int i=0;i<50;i++)
             {clientsList.add(dbServiceClient.saveClient(newClient(i)));
        }

        List<Optional<Client>> clientsListOpt = new ArrayList<>();

        for (int c=1;c<=2;c++) {
            long startTimeDB = System.currentTimeMillis();

            for (int i = 0; i < 50; i++) {
                clientsListOpt.add(
                        dbServiceClient.getClient(clientsList.get(i).getId())
                );
            }

            long finishTimeDB = System.currentTimeMillis();
            log.info("Fetched from "+switch (c){case 1->"DB";default -> "CACHE";}+":{} ms", finishTimeDB - startTimeDB);

            clientsListOpt.clear();
        }

    }

    private static Client newClient(int id) {
        return new Client(
                null,
                "Василий "+id,
                new Address(null, "AnyStreet"),
                List.of(
                        new Phone(null, "13-555-22"),
                        new Phone(null, "14-666-333")
                )
        );
    }
}
