package com.eliasnogueira.parametros;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

@RunWith(Parameterized.class)
public class ParametrizacaoJUnit {

	private String nome;
	private String cidade;
	private String faixa;
	private String resultado;
	
	private WebDriver driver;
	
	@Before
	public void preCondicao() {
		driver = new FirefoxDriver();
		driver.get("http://eliasnogueira.com/arquivos_blog/post_parametros/");
	}
	
	@After
	public void posCondicao() {
		driver.quit();
	}
	
	public ParametrizacaoJUnit(String nome, String cidade, String faixa, String resultado) {
		this.nome = nome;
		this.cidade = cidade;
		this.faixa = faixa;
		this.resultado = resultado;
	}
	
	@Test
	public void testeParametrizacaoJUnit() {
		driver.findElement(By.id("nome")).sendKeys(nome);
		driver.findElement(By.id("cidade")).sendKeys(cidade);
		new Select(driver.findElement(By.name("faixa"))).selectByVisibleText(faixa);
		driver.findElement(By.cssSelector(".btn.btn-primary.nextBtn.btn-lg.pull-right")).click();
		
		assertEquals(nome, driver.findElement(By.cssSelector("span[ng-bind='nome']")).getText());
		assertEquals(cidade, driver.findElement(By.cssSelector("span[ng-bind='cidade']")).getText());
		assertEquals(faixa, driver.findElement(By.cssSelector("span[ng-bind='faixaSelecionada']")).getText());
		assertEquals(resultado, driver.findElement(By.cssSelector("span[ng-bind='retorno']")).getText());
	}
	
	@Parameters(name = "{index}: {0}|{1}|{2}|{3}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{"Jose", "São Paulo", "Até 18 anos", "Os juros serão de 60%"},
			{"Maria", "Belo Horizonte", "Entre 19 e 25 anos", "Os juros serão de 40%"},
			{"João", "Florianópolis", "Entre 26 e 60 anos", "Os juros serão de 30%"},
			{"Carla", "Vitória", "Maior que 60 anos", "Os juros serão de 20%"}
		});
	}
	

}
