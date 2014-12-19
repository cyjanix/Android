package kontakty;

public class Osoba {
	
	public Osoba(String nazwa, String nrTel, String e_mail)
	{
		this.nazwa = nazwa;
		this.nrTel = nrTel;
		this.e_mail = e_mail;
	}
	
	private 
		String nazwa;
		public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getNrTel() {
		return nrTel;
	}

	public void setNrTel(String nrTel) {
		this.nrTel = nrTel;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

		String nrTel;
		String e_mail;
		
}
