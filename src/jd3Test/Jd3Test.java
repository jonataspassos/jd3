package jd3Test;

import function.ScaleBand;
import select.F;
import visualization.Visualization;

public class Jd3Test {

	public static void main(String[] args) throws Exception {
		Visualization vis = new Visualization();
		vis.tStyle().append(".container").attr("background-color","white");
		
		String[][] params = { { "yellow", "black", "10", "10", "5" }, { "blue", "orange", "50", "10", "7" },
				{ "red", "yellow", "10", "50", "7" }, { "green", "pink", "50", "50", "10" } };
		
		vis.tDynamic().selectAll(".circle").data(params).enter().append("circle").attr("class", "circle").attr("cx", new F() {
			public String f(Object d, int i) {
				return ((String[]) d)[2];
			}
		}).attr("cy", new F() {
			public String f(Object d, int i) {
				return ((String[]) d)[3];
			}
		}).attr("r", new F() {
			public String f(Object d, int i) {
				return ((String[]) d)[4];
			}
		}).attr("fill", new F() {
			public String f(Object d, int i) {
				return ((String[]) d)[1];
			}
		}).attr("stroke", new F() {
			public String f(Object d, int i) {
				return ((String[]) d)[0];
			}
		}).attr("stroke-width", 4);

		vis.fileOut("lari.html");

		Integer[][] params2 = { { 0, 1, 2, 3 }, { 4, 5, 6, 7 }, { 8, 9, 10, 11 }, { 12, 13, 14, 15 } };
		
		ScaleBand x = new ScaleBand().domain(params2[0]).range(25,125);
		ScaleBand y = new ScaleBand().domain(params2).range(25,125);

		vis = new Visualization();
		vis.tDynamic().selectAll(".row").data(params2).enter().append("g").attr("class", "row").attr("transform", new F() {
			public String f(Object d, int i) {
				return "translate(0," + y.apply(d) + ")";
			}
		}).selectAll(".col").data(new F() {
			public Object[] d(int i) {
				return params2[i];
			}
		}).enter().append("g").attr("class", "col").attr("transform", new F() {
			public String f(Object d, int i) {
				return "translate(" + x.apply(((Integer)d)%params.length) + ",0)";
			}
		}).append("text").innerText(new F() {
			public String f(Object d, int i) {
				return "" + d;
			}
		});

		vis.fileOut("joninhas.html");
		
		//vis.fileOut("vis.html");

	}

}
