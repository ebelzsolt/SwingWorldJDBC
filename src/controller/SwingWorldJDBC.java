package controller;

import model.Country;
import model.City;
import view.MainWindow;

/**
 *
 * @author Ébel Zsolt
 */
public class SwingWorldJDBC {

    public static void main(String[] args) {
        City city = new City();
        Country country = new Country();
        MainWindow mw = new MainWindow();
        Controller controller = new Controller(city, country, mw);            
    }
}
