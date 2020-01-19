package visualization;

import function.JD3String;
import select.F;
import select.Select;
import select.Tag;

public class Visualization {
	private Tag html;
	private String zoonables;

	public static void main(String[] args) throws Exception {
		Visualization vis = new Visualization();
		vis.tStyle().append(".container").attr("background-color","white");
		vis.fileOut("vis.html");
	}

	public Visualization() throws Exception {
		this(".:: Visualization ::.", 640, 480,"g");
	}

	public Visualization(String tittle, int width, int height,String zoonables) throws Exception {
		html = new Tag("html", null);
		Tag head = html.append("head");
		Tag body = html.append("body");
		head.append("title").innerText(tittle);
		
		this.zoonables = zoonables;
		head.append("style");
		
		Tag svg = body
				.append("div")
				.style("border", "0")
				.style("color", "#0000ff")
				.style("border", "6")
				.attr("class", "container").append("svg").attr("width", width).attr("height",
				height);
		svg.append("g").attr("id","dynamic").append("g").attr("class","in");
		svg.append("g").attr("id","static").append("g").attr("class","in");
		String [][] controls= {
				{"zoom in","zoom(true)"},
				{"zoom out","zoom(false)"},
				{"X +","translateX(true)"},
				{"X -","translateX(false)"},
				{"Y +","translateY(true)"},
				{"Y -","translateY(false)"},
				{"reset","reset()"},
				{"help","help()"}
		};
		body.append("div").attr("id","panel-control").attr("class","panel-control")
			.selectAll("button").data(controls).enter().append("button")
				.attr("onclick",new F() {
				public String f(Object d, int i) {
					return ((String[])d)[1];
				}}).innerText(new F() {
					public String f(Object d, int i) {
						return ((String[])d)[0];
					}});
		body.append("script").innerText(this.javaScript());
		//TODO
		/**
		 * Fazer o código css, que represente a visualização final
		 * */
	}
	
	public Visualization tittle(String tittle) {
		this.html.select("tittle").innerText(tittle);
		return this;
	}
	public Select tDynamic() {
		return html.select("#dynamic").select(".in");
	}
	public Select tStatic() {
		return html.select("#static").select(".in");
	}
	public Visualization resetDynamic() {
		html.select("#dynamic").select(".in").remove();
		html.select("#dynamic").append("g").attr("class","in");
		return this;
	}
	public Visualization resetStatic() {
		html.select("#static").select(".in").remove();
		html.select("#static").append("g").attr("class","in");
		return this;
	}
	public Select tStyle() {
		return html.select("style");
	}
	
	public Visualization fileOut(String path) {
		this.html.fileOut(path);
		return this;
	}
	
	private String javaScript() {
		return JD3String.fileIn("..\\jd3\\files\\visualization.js");
	}

	@Override
	public String toString() {
		return ""+this.html;
	}

	public void setZoonables(String zoonables) {
		this.zoonables = zoonables;
	}
	
	

	
}
