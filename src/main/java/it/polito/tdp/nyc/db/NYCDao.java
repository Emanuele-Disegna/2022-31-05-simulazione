package it.polito.tdp.nyc.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javadocmd.simplelatlng.LatLng;

import it.polito.tdp.nyc.model.Adiacenze;
import it.polito.tdp.nyc.model.Hotspot;

public class NYCDao {
	
	public List<Hotspot> getAllHotspot(String provider){
		String sql = "SELECT * FROM nyc_wifi_hotspot_locations WHERE provider=?";
		List<Hotspot> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, provider);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Hotspot(res.getInt("OBJECTID"), res.getString("Borough"),
						res.getString("Type"), res.getString("Provider"), res.getString("Name"),
						res.getString("Location"),res.getDouble("Latitude"),res.getDouble("Longitude"),
						res.getString("Location_T"),res.getString("City"),res.getString("SSID"),
						res.getString("SourceID"),res.getInt("BoroCode"),res.getString("BoroName"),
						res.getString("NTACode"), res.getString("NTAName"), res.getInt("Postcode")));
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}
	
	
	public List<String> getNodi(String provider){
		String sql = "SELECT distinct city "
				+ "FROM nyc_wifi_hotspot_locations "
				+ "WHERE provider=?";
		List<String> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, provider);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(res.getString("city"));
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}
	
	public List<Adiacenze> getAdiacenze(String provider){
		String sql = "SELECT DISTINCT n1.City AS c1, n2.City AS c2, AVG(n1.Latitude) AS lat1, AVG(n1.Longitude) AS lng1, "
				+ "AVG(n2.Latitude) AS lat2, AVG(n2.Longitude) AS lng2 "
				+ "FROM nyc_wifi_hotspot_locations AS n1, nyc_wifi_hotspot_locations AS n2 "
				+ "WHERE n1.Provider=? AND n1.Provider=n2.Provider AND n1.City>n2.City "
				+ "GROUP BY n1.City, n2.City";
		List<Adiacenze> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, provider);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Adiacenze a = new Adiacenze(res.getString("c1"), res.getString("c2"),
									new LatLng(res.getDouble("lat1"), res.getDouble("lng1")),
									new LatLng(res.getDouble("lat2"), res.getDouble("lng2")));
				result.add(a);
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}
	
	public List<String> getProviders(){
		String sql = "SELECT distinct provider "
				+ "FROM nyc_wifi_hotspot_locations ";
		
		List<String> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(res.getString("provider"));
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}
}
