package br.com.infox.util;

//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.view.JasperViewer;
//
//import java.translations.InputStream;
//import java.sql.Connection;
//import java.util.HashMap;

public class PrintReport {

//	private final Connection CONEXAO = ConexaoDAO.getConnection();
//	private InputStream URL;
//	private HashMap<String, Object> params;
//
//	public PrintReport(String url) {
//		this.URL = getClass().getResourceAsStream(url);
//	}
//
//	public PrintReport(String url, HashMap<String, Object> params) {
//		this.URL = getClass().getResourceAsStream(url);
//		this.params = params;
//	}
//
//	public void showReport() {
//		try {
//			JasperPrint jasperPrint = JasperFillManager.fillReport(URL, params, CONEXAO);
//			JasperViewer.viewReport(jasperPrint, false);
//		} catch (JRException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void setParams(HashMap<String, Object> params) {
//		this.params = params;
//	}
//
//	public void setURL(InputStream URL) {
//		this.URL = URL;
//	}
//
//	public HashMap<String, Object> getParams() {
//		return params;
//	}
//
//	public InputStream getURL() {
//		return URL;
//	}
}
