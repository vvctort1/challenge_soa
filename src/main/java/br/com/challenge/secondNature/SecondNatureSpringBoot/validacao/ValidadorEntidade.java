package br.com.challenge.secondNature.SecondNatureSpringBoot.validacao;

public interface ValidadorEntidade<T> {
    void validar(T entidade);
    boolean podeExecutar(T entidade);
}
