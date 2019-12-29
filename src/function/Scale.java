package function;

public abstract class Scale {
	private float [] rangeValue = {0.0f,100.0f};
	
	public abstract float apply(Object v) throws Exception;

	protected float[] getRangeValue() {
		return rangeValue;
	}

	protected void setRangeValue(float[] rangeValue) throws Exception {
		if(rangeValue!= null && rangeValue.length==2)
			this.rangeValue = rangeValue;
		else {
			System.err.println("Range deve ser um vetor de dois elementos");
			throw new Exception();
		}
			
	}
	
	public Scale range(float[] rangeValue) throws Exception {
		this.setRangeValue(rangeValue);
		return this;
	}
	
	public Scale range(float s, float e) throws Exception {
		return range(setRange(s, e));
	}
	
	public float[] range() {
		return getRangeValue();
	}
	
	public static float [] setRange(float s, float e) {
		float [] ret = {s,e};
		return ret;
	}
	
	
}
