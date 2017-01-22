package controller;

import java.awt.EventQueue;
import view.CitiesDialog;
import model.Country;
import model.City;
import view.MainWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Ébel Zsolt
 */
public class Controller {

    private City city;
    private Country country;
    private MainWindow mainWindow;
    private CitiesDialog citiesDialog;
    private Country selectedCountry;
    List<Country> countries;
    List<City> cities;

    public Controller() {
    }

    public Controller(City city, Country country, MainWindow mainWindow) {
        this.city = city;
        this.country = country;
        this.mainWindow = mainWindow;
        selectedCountry = null;
        mainWindow.addMenuActionListener(new MenuActionListener());
    }

    private class MenuActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == mainWindow.getExitMenu()) {
                System.exit(0);
            } else if (e.getSource() == mainWindow.getLoadCitiesMenu()) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        citiesDialog = new CitiesDialog();
                        citiesDialog.addMouseListener(new ListMouseListener());
                        citiesDialog.addDeleteButtonListener(new DeleteButtonActionListener());
                        
                        countries = country.getCountries();
                        Vector vector = new Vector();

                        for (Country ct : countries) {
                            vector.add(ct.getName());
                        }
                        citiesDialog.loadCountryNames(vector);
                    }
                });

            }
        }
    }

    private class DeleteButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = citiesDialog.getSelectedRow();

            if ((selectedCountry != null) && (selectedRow >= 0)) {
                city.deleteCity(cities.get(selectedRow));
                citiesDialog.removeRow(selectedRow);
            }
        }
    }

    private class ListMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            citiesDialog.clearTable();
            selectedCountry = countries.get(citiesDialog.getSelectedIndex());
            cities = city.getCities(selectedCountry);

            for (City c : cities) {
                Vector row = new Vector();
                row.addElement(c.getName());
                row.addElement(c.getDistrict());
                row.addElement(String.format("%, d", c.getPopulation()));
                citiesDialog.fillTableData(row);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}
