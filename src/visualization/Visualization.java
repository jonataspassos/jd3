package visualization;

import select.F;
import select.Select;
import select.Tag;

public class Visualization {
	private Tag html;
	private float factor = 0.1f;
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
		System.out.println(this.zoonables);
		return "var zoom, translateX, translateY, reset, help;\r\n" + 
				"\r\n" + 
				"        (function () {\r\n" + 
				"\r\n" + 
				"            var KEY_DOWN = 40, KEY_UP = 38, KEY_LEFT = 37, KEY_RIGHT = 39;\r\n" + 
				"            var KEY_END = 35, KEY_BEGIN = 36;\r\n" + 
				"            var KEY_BACK_TAB = 8, KEY_TAB = 9, KEY_SH_TAB = 16, KEY_ENTER = 13, KEY_ESC = 27, KEY_SPACE = 32, KEY_DEL = 46;\r\n" + 
				"            var KEY_A = 65, KEY_B = 66, KEY_C = 67, KEY_D = 68, KEY_E = 69, KEY_F = 70, KEY_G = 71, KEY_H = 72, KEY_I = 73,\r\n" + 
				"                KEY_J = 74, KEY_K = 75, KEY_L = 76, KEY_M = 77, KEY_N = 78, KEY_O = 79, KEY_P = 80, KEY_Q = 81, KEY_R = 82,\r\n" + 
				"                KEY_S = 83, KEY_T = 84, KEY_U = 85, KEY_V = 86, KEY_W = 87, KEY_X = 88, KEY_Y = 89, KEY_Z = 90;\r\n" + 
				"            var KEY_PF1 = 112, KEY_PF2 = 113, KEY_PF3 = 114, KEY_PF4 = 115, KEY_PF5 = 116, KEY_PF6 = 117, KEY_PF7 = 118, KEY_PF8 = 119;\r\n" + 
				"\r\n" + 
				"            var REMAP_KEY_T = 5019;\r\n" + 
				"\r\n" + 
				"            var factor = "+this.factor+";\r\n" + 
				"            var z = 1.0;\r\n" + 
				"            var tx = 0;\r\n" + 
				"            var ty = 0;\r\n" + 
				"            function setTransform() {\r\n" + 
				"                document.querySelector(\"#dynamic\").setAttribute(\"transform\", \"translate(\" + tx + \",\" + ty + \")scale(\" + z + \",\" + z + \")\");\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            function w() {\r\n" + 
				"                return document.querySelector(\"svg\").getBoundingClientRect().width;\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            function h() {\r\n" + 
				"                return document.querySelector(\"svg\").getBoundingClientRect().height;\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            function checkEventObj(_event_) {\r\n" + 
				"                // --- IE explorer\r\n" + 
				"                if (window.event)\r\n" + 
				"                    return window.event;\r\n" + 
				"                // --- Netscape and other explorers\r\n" + 
				"                else\r\n" + 
				"                    return _event_;\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            zoom = function (s) {\r\n" + 
				"                if (s == false) {\r\n" + 
				"                    z /= 1 + factor;\r\n" + 
				"                    tx /= 1 + factor;\r\n" + 
				"                    ty /= 1 + factor;\r\n" + 
				"                } else {\r\n" + 
				"                    z *= 1 + factor;\r\n" + 
				"                    tx *= 1 + factor;\r\n" + 
				"                    ty *= 1 + factor;\r\n" + 
				"                }\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"                setTransform()\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            translateX = function (s) {\r\n" + 
				"                if (s == false) {\r\n" + 
				"                    tx += w() * factor;\r\n" + 
				"                } else {\r\n" + 
				"                    tx -= w() * factor;\r\n" + 
				"                }\r\n" + 
				"                setTransform()\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            translateY = function (s) {\r\n" + 
				"                if (s == false) {\r\n" + 
				"                    ty += h() * factor;\r\n" + 
				"                } else {\r\n" + 
				"                    ty -= h() * factor;\r\n" + 
				"                }\r\n" + 
				"                setTransform()\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            reset = function () {\r\n" + 
				"                z = 1.0;\r\n" + 
				"                tx = 0;\r\n" + 
				"                ty = 0;\r\n" + 
				"                setTransform();\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            help = function(){\r\n" + 
				"                alert(\"This is a visualization of SVG.\\n\"+\r\n" + 
				"                    \"You can:\\n\"+\r\n" + 
				"                    \"   Change Zoom:\\n\"+\r\n" + 
				"                    \"       Zoom        (Ctrl + Up_arrow , Ctrl + Dowm_arrow)\\n\"+\r\n" + 
				"                    \"   Change translation:\\n\"+\r\n" + 
				"                    \"       Translate X (Left_arrow , Right_arrow)\\n\"+\r\n" + 
				"                    \"       Translate Y (Up_arrow , Down_arrow)\\n\"+\r\n" + 
				"                    \"   Reset View (R)\\n\"\r\n" + 
				"                        );\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            document.onkeydown = function (_event_) {\r\n" + 
				"                var winObj = checkEventObj(_event_);\r\n" + 
				"                var intKeyCode = winObj.keyCode;\r\n" + 
				"                var intAltKey = winObj.altKey;\r\n" + 
				"                var intCtrlKey = winObj.ctrlKey;\r\n" + 
				"            	function finishKey() {\r\n" + 
				"              	  winObj.keyCode = intKeyCode = REMAP_KEY_T;\r\n" + 
				"              	  winObj.returnValue = false;\r\n" + 
				"           	     return false;\r\n" + 
				"        	    }\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"                // 1° --- Access with [ALT/CTRL+Key]\r\n" + 
				"                if (intAltKey || intCtrlKey) {\r\n" + 
				"\r\n" + 
				"                    if (intKeyCode == KEY_RIGHT || intKeyCode == KEY_UP) {\r\n" + 
				"                        zoom(true);\r\n" + 
				"                        return finishKey();\r\n" + 
				"                    } else if (intKeyCode == KEY_LEFT || intKeyCode == KEY_DOWN) {\r\n" + 
				"                        zoom(false);\r\n" + 
				"                        return finishKey();\r\n" + 
				"                    } else if (intKeyCode == KEY_R){\r\n" + 
				"                        reset();\r\n" + 
				"                        return finishKey();\r\n" + 
				"                    }\r\n" + 
				"                }\r\n" + 
				"                // 2 ° --- Access without [ALT/CTRL+Key]\r\n" + 
				"                else {\r\n" + 
				"\r\n" + 
				"                    if (intKeyCode == KEY_RIGHT) {\r\n" + 
				"                        translateX(true);\r\n" + 
				"                        return finishKey();\r\n" + 
				"                    } else if (intKeyCode == KEY_LEFT) {\r\n" + 
				"                        translateX(false);\r\n" + 
				"                        return finishKey();\r\n" + 
				"                    } else if (intKeyCode == KEY_UP) {\r\n" + 
				"                        translateY(false);\r\n" + 
				"                        return finishKey();\r\n" + 
				"                    } else if (intKeyCode == KEY_DOWN) {\r\n" + 
				"                        translateY(true);\r\n" + 
				"                        return finishKey();\r\n" + 
				"                    } else if (intKeyCode == KEY_R){\r\n" + 
				"                        reset();\r\n" + 
				"                        return finishKey();\r\n" + 
				"                    }\r\n" + 
				"\r\n" + 
				"                }\r\n" + 
				"            }\r\n" + 
				"			 var zoonables = document.querySelectorAll(\""+(this.zoonables)+"\");\r\n" + 
				"            zoonables.forEach(function(d,i){\r\n" + 
				"                d.setAttribute(\"style\",\"cursor: pointer;\");\r\n" + 
				"                d.addEventListener(\"click\",function(){\r\n" + 
				"                    reset();\r\n" + 
				"                    var svg = document.querySelector(\"svg\");\r\n" + 
				"                    \r\n" + 
				"                    var w = this.getBoundingClientRect();\r\n" + 
				"                    var h = w.height*z,\r\n" + 
				"                        x = (w.x-svg.getBoundingClientRect().x)*z,\r\n" + 
				"                        y = (w.y-svg.getBoundingClientRect().y)*z;\r\n" + 
				"                    w = w.width*z;\r\n" + 
				"\r\n" + 
				"                    x-=w/2;\r\n" + 
				"                    w+=w/2;\r\n" + 
				"                    \r\n" + 
				"                    z = Math.min(svg.getBoundingClientRect().width/w,svg.getBoundingClientRect().height/h);\r\n" + 
				"                    tx = -x*z;\r\n" + 
				"                    ty = -y*z;\r\n" + 
				"\r\n" + 
				"                    setTransform();\r\n" + 
				"                },false);\r\n" + 
				"            });\r\n" + 
				"        })()";
	}
	
	public Visualization setFactor(float factor) {
		this.factor = factor;
		return this;
	}

	@Override
	public String toString() {
		return ""+this.html;
	}

	public void setZoonables(String zoonables) {
		this.zoonables = zoonables;
	}
	
	

	
}
