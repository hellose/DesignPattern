package structural.decorator.afteruse;

//실제 !!!상속으로 서브클래스를 계속 만드는 방법이 실질적이지 못할 때!!! 사용
//너무 많은 수의 독립된 확장이 가능한 경우가 존재할때 !!!모든 조합을 지원하기 위해 사용!!!
class Program {

	public static void main(String[] args) {

		View view = null;

		System.out.println("--- 테스트1: 텍스트 그리기 데코레이션 ---");
		view = new TextDecorator(new View());
		// 1. TextDecorator의 draw메서드 호출
		view.draw();

		// super.draw(); -> 2. TextDecorator가 포함하고 있는 View객체의 draw()가 먼저 호출된다.
		// "View 배경 그리기"
		// drawText();
		// "글자 그리기"
		System.out.println();

		// 데코레이팅할 기능을 할 클래스를 먼저 선언 해둬야하는 것은 데코레이터 패턴을 사용하기 전과 동일하지만
		// 데코레이터 패턴을 활용하면 동적으로 데코레이팅 순서를 조절할 수 있다.
		System.out.println("--- 테스트2: 중첩 데코레이션이 가능하며 동적으로 데코레이션 연산 순서를 변경할 수 있다. ---");
		view = new BoarderDecorator(new TextDecorator(new View()));
		view.draw();
		System.out.println();
		// 동적으로 데코레이팅 순서 변경 가능
		view = new TextDecorator(new BoarderDecorator(new View()));
		view.draw();
		System.out.println();

		// 데코레이터 클래스에 데코레이팅 될 타겟이 View 타입이므로 모든 View가 꾸며질 수 있다.
		System.out.println("--- 테스트3: 중요한 점은 Decoration 객체는 모든 View 타입을 데코레이션 할 수 있다는 것이다 ---");
		view = new TextDecorator(new View());
		view.draw();
		System.out.println();

		view = new TextDecorator(new SubView1());
		view.draw();
		System.out.println();

		view = new TextDecorator(new SubView2());
		view.draw();
		System.out.println();

		view = new TextDecorator(new SubSubView1());
		view.draw();
		System.out.println();

	}

}

//데코레이팅의 대상
class View {
	void draw() {
		System.out.println("View 배경 그리기");
	}
}

//데코레이터(장식자)
abstract class ViewDecorator extends View {
	// decoration될 타겟 View (합성 관계)
	private View decoratedView;

	ViewDecorator(View decoratedView) {
		this.decoratedView = decoratedView;
	}

	@Override
	void draw() {
		// 자신이 소유한 component의 draw를 호출하는 것이 핵심!!!
		decoratedView.draw();
	}
}

//글자 데코레이터
class TextDecorator extends ViewDecorator {

	TextDecorator(View textDecoratedTargetView) {
		super(textDecoratedTargetView);
	}

	@Override
	void draw() {
		super.draw();
		drawText();
	}

	private void drawText() {
		System.out.println("글자 그리기");
	}
}

//경계선 데코레이터
class BoarderDecorator extends ViewDecorator {

	BoarderDecorator(View boarderDecoratedTargetView) {
		super(boarderDecoratedTargetView);
	}

	@Override
	void draw() {
		super.draw();
		drawBoarder();
	}

	private void drawBoarder() {
		System.out.println("경계선 그리기");
	}
}

//스크롤 데코레이터
class ScrollDecorator extends ViewDecorator {

	ScrollDecorator(View scrollDecoratedTargetView) {
		super(scrollDecoratedTargetView);
	}

	@Override
	void draw() {
		super.draw();
		drawScroll();
	}

	private void drawScroll() {
		System.out.println("스크롤 그리기");
	}
}

class SubView1 extends View {
	@Override
	void draw() {
		System.out.println("SubView1 배경 그리기");
	}
}

class SubView2 extends View {
	@Override
	void draw() {
		System.out.println("SubView2 배경 그리기");
	}
}

class SubSubView1 extends SubView1 {
	@Override
	void draw() {
		System.out.println("SubSubView1 배경 그리기");
	}
}