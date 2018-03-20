package kredyt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mockito;

import servlets.Kredyt;

public class KredytTest extends Mockito
{
	
	@Test
	public void servlet_should_not_display_the_loan_repayment_schedule_if_any_of_the_values_is_null() throws IOException
	{
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		PrintWriter writer = mock(PrintWriter.class);
		
		when(response.getWriter()).thenReturn(writer);
		
		Kredyt servlet = new Kredyt();
		
		when(request.getParameter("kwota")).thenReturn(null);
		when(request.getParameter("ilosc")).thenReturn(null);
		when(request.getParameter("oprocentowanie")).thenReturn(null);
		when(request.getParameter("oplata")).thenReturn(null);
		when(request.getParameter("rodzaj")).thenReturn(null);
		
		servlet.doPost(request, response);
		
		verify(response).sendRedirect("/");
	}
	
	@Test
	public void servlet_should_not_display_the_loan_repayment_schedule_if_any_of_the_values_is_empty() throws IOException
	{
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		PrintWriter writer = mock(PrintWriter.class);
		
		when(response.getWriter()).thenReturn(writer);
		
		Kredyt servlet = new Kredyt();
		
		when(request.getParameter("kwota")).thenReturn("");
		when(request.getParameter("ilosc")).thenReturn("");
		when(request.getParameter("oprocentowanie")).thenReturn("");
		when(request.getParameter("oplata")).thenReturn("");
		when(request.getParameter("rodzaj")).thenReturn("");
		
		servlet.doPost(request, response);
		
		verify(response).sendRedirect("/");
	}
	
	@Test
	public void servlet_should_not_display_the_loan_repayment_schedule_if_the_rodzaj_is_not_stala_or_malejaca() throws IOException
	{
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		PrintWriter writer = mock(PrintWriter.class);
		
		when(response.getWriter()).thenReturn(writer);
		
		Kredyt servlet = new Kredyt();
		
		when(request.getParameter("rodzaj")).thenReturn("inna");
		
		servlet.doPost(request, response);
		
		verify(response).sendRedirect("/");
	}
	@Test
	public void servlet_should_display_the_loan_repayment_schedule_when_the_values_are_provided() throws IOException
	{
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		PrintWriter writer = mock(PrintWriter.class);
		
		when(request.getParameter("kwota")).thenReturn("3000");
		when(request.getParameter("ilosc")).thenReturn("12");
		when(request.getParameter("oprocentowanie")).thenReturn("12");
		when(request.getParameter("oplata")).thenReturn("5");
		when(request.getParameter("rodzaj")).thenReturn("stala");
		
		when(response.getWriter()).thenReturn(writer);
		
		new Kredyt().doPost(request, response);
		
		verify(writer).println("<h1>Harmonogram splat kredytu</h1>");
	}

}
