package function;

public class ScaleLinear extends Scale{
	
	private float [] domainValue = {0.0f,1.0f};

	protected float[] getDomainValue() {
		return domainValue;
	}

	protected void setDomainValue(float[] domainValue) throws Exception {
		if(domainValue!=null && domainValue.length == 2)
			this.domainValue = domainValue;
		else {
			System.err.println("Domain deve ser um vetor de dois elementos");
			throw new Exception();
		}
	}
	
	public ScaleLinear domain(float [] domain) throws Exception {
		setDomainValue(domain);
		return this;
	}
	
	public float [] domain() {
		return getDomainValue();
	}
	
	public ScaleLinear range(float [] range) {
		return range(range);
	}

	@Override
	public float apply(Object v) throws Exception {
		if(v instanceof Integer || v instanceof Float || v instanceof Double) {
			Float value = (Float)v;
			return ((value-this.domainValue[0])/(this.domainValue[1]-this.domainValue[0]))
					*(getRangeValue()[1]-getRangeValue()[0])
					+getRangeValue()[0];
		}else {
			System.err.println("O tipo de entrada deve ser numérico");
			throw new Exception();
		}
	}
	
	

	

}
