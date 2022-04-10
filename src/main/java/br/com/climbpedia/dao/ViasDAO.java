package br.com.climbpedia.dao;

public class ViasDAO {

	public static String getVias(int id) {
		if(id == 1) {
			return "Via 1 \n Via dos Italianos \n 5° grau E2 D3 \n muito top";
		}
		return "Outra via \n K2 \n 4° grau E3 D3 \n visual estonteante";
	}
}
