
package TrabalhoSO;

public class Processo 
{
    private String nome;
    private int tempo;
    private int PID;
    
    
    public void setnome(String nome)
    {
       this.nome = nome;
    }
    public void settempo(int tempo)
    {
       this.tempo = tempo;
    }
    public String getnome() 
    {
        return nome;
    }
    public int gettempo() 
    {
        return tempo;
    }

    public int getPID()
    {
        return PID;
    }

    public void setPID(int PID)
    {
        this.PID = PID;
    }
    
}
