
public class Semaforo implements Runnable {
	private CorSemaforo cor;
	private boolean parar;
	private boolean corMundan�a;

	public Semaforo() {
		this.cor = CorSemaforo.VERMELHO;

		this.parar = false;
		this.corMundan�a = false;
		
		new Thread(this).start();
	}

	
	public enum CorSemaforo {
		
		VERDE, AMARELO, VERMELHO
	}
	
	
	@Override
	public void run() {

		while(!parar) {
			try {
				switch(this.cor) {
				case VERMELHO:
					Thread.sleep(60000);
					break;
					
				case AMARELO:
					Thread.sleep(30000);
					break;
					
				case VERDE:
					Thread.sleep(120000);
					break;

				default:
					break;
				}
				this.mudarCor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private synchronized void mudarCor() {
		switch (this.cor) {
		case VERMELHO: 
				this.cor = CorSemaforo.VERDE;
			break;
			
		case AMARELO: 
				this.cor = CorSemaforo.VERMELHO;
			break;
			
		case VERDE: 
				this.cor = CorSemaforo.AMARELO;
			break;
			
		default:
			break;
		}
		this.corMundan�a = true;
		notify();
	}

	public synchronized void mudou() {
		while(!this.corMundan�a) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.corMundan�a = false;
	}
	
	public CorSemaforo getCor() {
		return cor;
	}
}
