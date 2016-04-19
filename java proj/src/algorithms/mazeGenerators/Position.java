package algorithms.mazeGenerators;

public class Position {
	private int x;// column number
	private int y;// row number
	private int z;// height/floor

	public Position(int z, int y, int x) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void setPos(int z, int y, int x) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	@Override
	public String toString() {
		String str = "{" + z + "," + y + "," + x + "}";
		return str;
	}

	public boolean equals(Object p){
		if((p instanceof Position)==false)
		{
			return false;
		}
		return ((this.x==((Position)p).x)&&(this.y==((Position)p).y)&&(this.z==((Position)p).z));
	}

	@Override
	public int hashCode() {
		
		return this.toString().hashCode();
	}
	
}
