package servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/kredyt")
public class Kredyt extends HttpServlet
{

	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{	
		String kwota = request.getParameter("kwota");
		String ilosc = request.getParameter("ilosc");
		String oprocentowanie = request.getParameter("oprocentowanie");
	    String oplata = request.getParameter("oplata");
		String rodzaj = request.getParameter("rodzaj");
		
		if(kwota==null || ilosc==null || oprocentowanie==null || oplata==null || rodzaj==null)
		{
			response.sendRedirect("/");
		}
		else if(kwota.equals("") || ilosc.equals("") || oprocentowanie.equals("") || oplata.equals("") || rodzaj.equals(""))
	    {
			response.sendRedirect("/");
		}
		else
		{
		    Double kwotaD = Double.parseDouble(kwota);
		    Integer iloscI = Integer.parseInt(ilosc);
		    Integer oprocentowanieI = Integer.parseInt(oprocentowanie);
	        Integer oplataI = Integer.parseInt(oplata);		
		
		    if(kwotaD<=0 || iloscI<=0 || oprocentowanieI<0 || oprocentowanieI>100 || oplataI<0 || oplataI>100)
		    {
			    response.sendRedirect("/");
		    }
		
	    	response.setContentType("text/html");
		    response.getWriter().println("<h1>Harmonogram splat kredytu</h1>");
		    response.getWriter().println("<table border=2>"
		            + "<tr>" 
		    	    + "<th>Nr raty</th>"
		            + "<th>Kwota Kapitalu</th>"
		    	    + "<th>Kwota odsetek</th>"
		            + "<th>Oplaty stale</th>"
		    	    + "<th>Kwota raty</th>"
		    	    + "</tr>");
		    
		    double K=kwotaD;
		    int n=iloscI, r=oprocentowanieI, p=oplataI;
		    double q, R;
		
		    double[] S = new double[n];
	        double[] O = new double[n];
	        double[] C = new double[n];
		
	        K = K*(1 + (double)p/100);
	    
	        if(rodzaj.equalsIgnoreCase("stala"))
		    {
	        	
			    q = 1 + (double)r/1200;
			    R = (K*Math.pow(q, n)*(q-1))/(Math.pow(q, n)-1);	
							    
		        O[0] = K*((double)r/1200);
			    S[0] = R - K*((double)r/1200);
			    C[0] = K-S[0];
		    
			    response.getWriter().printf("<tr>"
	    			    + "<td> 1 </td>"
	    			    + "<td> %.2f </td>"
	    			    + "<td> %.2f </td>"
	    			    + "<td> %.2f </td>"
	    			    + "<td> %.2f </td>"
	    			    + "</tr>", C[0], O[0], S[0], R);
		        
		        for(int i=1; i<n; i++)
		        {
			        O[i] = C[i-1]*((double)r/1200);
		    	    S[i] = R - O[i];
		    	    C[i] = C[i-1] - S[i];
		    	    C[n-1] = 0;
		    	
		    	    response.getWriter().printf("<tr>"
		    			    + "<td> %d </td>"
		    			    + "<td> %.2f </td>"
		    			    + "<td> %.2f </td>"
		    			    + "<td> %.2f </td>"
		    			    + "<td> %.2f </td>"
		    			    + "</tr>", i+1, C[i], O[i], S[i], R);
		        }
		        response.getWriter().println("</table>");
		    }
		    else if(rodzaj.equalsIgnoreCase("malejaca"))
		    {
			    S[0] = K/n;
			    O[0] = K*((double)r/1200);
			    C[0] = K - S[0];
			
			    response.getWriter().printf("<tr>"
	    			    + "<td> 1 </td>"
	    			    + "<td> %.2f </td>"
	    			    + "<td> %.2f </td>"
	    			    + "<td> %.2f </td>"
	    			    + "<td> %.2f </td>"
	    			    + "</tr>", C[0], O[0], S[0], S[0]+O[0]);
			    
			    for(int i=1; i<n; i++)
			    {
				    S[i] = K/n;
				    O[i] = C[i-1]*(double)r/1200;
				    C[i] = C[i-1] - S[i];
				
				    response.getWriter().printf("<tr>"
		    			    + "<td> %d </td>"
		    			    + "<td> %.2f </td>"
		    			    + "<td> %.2f </td>"
		    			    + "<td> %.2f </td>"
		    			    + "<td> %.2f </td>"
		    			    + "</tr>", i+1, C[i], O[i], S[i], S[i]+O[i]);
			    }
			    response.getWriter().println("</table>");
		    }
		    else
		    {
			    response.sendRedirect("/");
		    }
		}
	}

}