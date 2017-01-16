package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Ébel Zsolt
 */
public class City implements dbConnection, Comparable<City> {

    private int ID;
    private String name;
    private Country country;
    private String district;
    private int population;

    public City() {
    }

    public City(int ID, String name, Country country, String district, int population) {
        this.ID = ID;
        this.name = name;
        this.country = country;
        this.district = district;
        this.population = population;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountryCode(Country country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
    
    @Override
    public int compareTo(City o) {
        return name.compareTo(o.name);
    }

    public List<City> getCities(Country country) {
        List<City> cities = new ArrayList<>();
        
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String selectStmt = "SELECT * FROM city "
                + "INNER JOIN country "
                + "ON city.CountryCode = country.Code "
                + "WHERE country.Code = ? ";
            PreparedStatement ps = con.prepareStatement(selectStmt);
            ps.setString(1, country.getCode());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ID = rs.getInt("ID");
                name = rs.getString("Name");                
                district = rs.getString("District");
                population = rs.getInt("Population");

                City city = new City(ID, name, country, district, population);
                cities.add(city);
            }
        } catch (SQLException ex) {
            System.out.println("SQLExeption - getCities()");
        }
        Collections.sort(cities);
        return cities;
    }

    public void deleteCity(City city) {                
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String deleteStmt = "DELETE FROM city "
                            + "WHERE ID = ?";
            PreparedStatement ps = con.prepareStatement(deleteStmt);
            ps.setInt(1, city.ID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQLExeption - deleteCity()");
        }
    }   
    
}
