package com.polskownia.shop;

import com.polskownia.utils.Category;
import com.polskownia.utils.Level;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
class ShopItemInitializer implements ApplicationListener<ContextRefreshedEvent>
{
    private final ShopRepository shopRepo;

    ShopItemInitializer(final ShopRepository shopRepo)
    {
        this.shopRepo = shopRepo;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event)
    {
        var title1 = "Indywidualny kurs do egzaminu B1";
        if (!this.shopRepo.existsByTitle(title1))
            this.shopRepo.save(new ShopItem(
                    title1
                    , 1500
                    , "krótki ind"
                    , "Indywidualne zajęcia skupione na rozwiązywaniu zadań egzaminacyjnych oraz wyłapywaniu trudności kursanta"
                    , Category.COURSE_INDIVIDUAL
                    , Level.B1
            ));

        var title2 = "Polski z Wioletką(A1)";
        if (!this.shopRepo.existsByTitle(title2))
            this.shopRepo.save(new ShopItem(
                    title2
                    , 50
                    , "krótkie wprowadzenie"
                    , "Wprowadzenie do zagadnień języka polskiego dla obcokrajowców"
                    , Category.BOOK
                    , Level.A1
            ));

        var title3 = "Symulacja zadania z mówienia państwowy B1";
        if (!this.shopRepo.existsByTitle(title3))
            this.shopRepo.save(new ShopItem(
                    title3
                    , 1500
                    , "krótkie ćwiczenie"
                    , "Ćwiczenie polegające na orpacowniu zadania z mówienia dla egzaminu państwowego B1"
                    , Category.EXAM_SIMULATION
                    , Level.B1
            ));

        var title4 = "Arkusz ćwiczeń liter polskiego alfabetu";
        if (!this.shopRepo.existsByTitle(title4))
            this.shopRepo.save(new ShopItem(
                    title4
                    , 1500
                    , "krótki arkusz"
                    , "Arkusz ćwiczeń zawierający wzory liter polskiego alfabetu do wprawiania się w ich pisaniu - PLIK PDF DO SAMODZIELNEGO WYDRUKU"
                    , Category.EXERCISE_SHEET
                    , Level.A1
            ));
    }
}
