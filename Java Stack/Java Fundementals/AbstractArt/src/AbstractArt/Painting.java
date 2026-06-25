package AbstractArt;

public class Painting extends Art{

	private String paintType;

	public Painting(String title, String author, String description ,String paintType ) {
		super(title, author, description);
		this.setPaintType(paintType);
	}

	@Override
	public void viewArt() {
		// TODO Auto-generated method stub
		System.out.println("Painting");
		System.out.println("Title :" + title);
		System.out.println("Author :" + author);
		System.out.println("Description :" + description);
		System.out.println("Paint Type :" + paintType);	
		System.out.println("-------------------------");
	}

	
	public String getPaintType() {
		return paintType;
	}

	public void setPaintType(String paintType) {
		this.paintType = paintType;
	}

}
