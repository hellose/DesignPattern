package structural.decorator.beforeuse;

class Program {
	public static void main(String[] args) {
		View v = new View();
		v.draw();
		System.out.println();

		v = new TextView();
		v.draw();
		System.out.println();

		v = new BoarderTextView();
		v.draw();
		System.out.println();

		v = new ScrollableTextView();
		v.draw();
		System.out.println();

	}
}

class View {
	void draw() {
		System.out.println("배경 그리기");
	}
}

//View의 하위
class TextView extends View {
	@Override
	void draw() {
		super.draw();
		drawText();
	}

	private void drawText() {
		System.out.println("글자 그리기");
	}
}

//View의 하위
class BoarderView extends View {
	@Override
	void draw() {
		super.draw();
		drawBoarder();
	}

	private void drawBoarder() {
		System.out.println("경계선 그리기");
	}
}

//TextView의 하위
class ScrollableTextView extends TextView {
	@Override
	void draw() {
		super.draw();
		drawScroll();
	}

	private void drawScroll() {
		System.out.println("스크롤 그리기");
	}
}

//TextView의 하위
class BoarderTextView extends TextView {
	@Override
	void draw() {
		super.draw();
		drawBoarder();
	}

	private void drawBoarder() {
		System.out.println("경계선 그리기");
	}
}