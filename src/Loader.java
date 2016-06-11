public class Loader extends gameView{
	
	
	public Loader(Core app){
		super("/afs/ericpol.int/home/m/l/mlak/home/star.jpg", app);
		objectList.add(new GameButton(45, 70, 10, 5, getClass().getResource("/images/startCiemny.png").getFile(), getClass().getResource("/images/startJasny.png").getFile(), getClass().getResource("/images/startOn.png").getFile(),this));
		objectList.add(new GameButton(45, 77, 10, 5, getClass().getResource("/images/exitCiemny.png").getFile(), getClass().getResource("/images/exitJasny.png").getFile(), getClass().getResource("/images/exitOn.png").getFile(),this));
	}

	@Override
	public void onClick() {
		
	}
}
