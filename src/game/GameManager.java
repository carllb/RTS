package game;

public class GameManager implements Runnable{

	Game game;
	boolean running;
	Thread gt;
	int FPS = 0;
	int targetFPS;
	
	
	public GameManager(Game game,int targetFPS){
		this.game = game;
		this.targetFPS = targetFPS;
	}
	
	
	public void startGame(){
		gt = new Thread(this);
		game.init();
		gt.start();
		running = true;
	}
	
	public void stopGame(){
		running = false;
	}
	
	public boolean getRunning(){
		return running;
	}
	
	public int getFPS(){
		return FPS;
	}
	
	public void setGameSpeed(int FPS){
		this.targetFPS = FPS;
	}

	long startTime;
	long endTime;

	long mStartTime;
	long mEndTime;


	@Override
	public void run() {
		while (running) {
			mStartTime = System.currentTimeMillis();

			startTime = System.currentTimeMillis();
			game.tick();			
			endTime = System.currentTimeMillis();
			long deltaTimeMS = endTime - startTime;
			float expectedTimeSec = (float) (1f / targetFPS);
			long etMS = (long) (expectedTimeSec * 1000);
			long diff = etMS - deltaTimeMS;
			if (diff > 0) {
				try {
					Thread.sleep(diff);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			mEndTime = System.currentTimeMillis();
			calculateFPS(mStartTime, mEndTime);
		}
		game.end();
	}
		
	void calculateFPS(long st, long et){
		long diff = mEndTime - mStartTime;
		if (diff > 0) {
			FPS = (int) (1000f / diff);
		} else {
			FPS = -1;
		}
	}
	
	public Thread getGameThread(){
		return gt;
	}
	
}
