package by.latashinsky.java.topics.factory;

import by.latashinsky.java.topics.exceptions.FactoryConfigurationException;
import by.latashinsky.java.topics.models.CurrencyExchangeRateHelper;
import by.latashinsky.java.topics.models.DataBaseCurrencyExchangeRateHelper;
import by.latashinsky.java.topics.models.JsonCurrencyExchangeRateHelper;
import by.latashinsky.java.topics.repositories.MyRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class Factory {
    private Factory() {
        configure();
    }

    private RepositoryFactory repositoryFactory;
    private static Factory instance;
    private CurrencyExchangeRateHelper currencyExchangeRateHelper;

    public <T> MyRepository<?> getRepository(Class<T> clazz) {
        return repositoryFactory.getRepository(clazz);
    }
    public CurrencyExchangeRateHelper getCurrencyExchangeRateHelper(){
        return currencyExchangeRateHelper;
    }
    public static Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    private void configure() {
        String rootPath = Objects.requireNonNull(
                Thread.currentThread().getContextClassLoader().getResource("")).getPath();
        String appConfigPath = rootPath + "configuration.properties";
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String profile = properties.getProperty("profile");
        switch (profile) {
            case "DB" : {
                repositoryFactory = new DataBaseRepositoryFactory();
                currencyExchangeRateHelper = new DataBaseCurrencyExchangeRateHelper();
                break;
            }
            case "JSON" :{
                repositoryFactory = new JsonRepositoryFactory();
                currencyExchangeRateHelper = new JsonCurrencyExchangeRateHelper();
                break;
            }
            default :{
                throw new FactoryConfigurationException();
            }
        }
    }
}
