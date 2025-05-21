package com.anduarte.dungeoncrawler.components;

/**
 * Componente responsável por armazenar e gerir a vida de uma entidade (ex: jogador).
 * Pode ser utilizado para atualizar a interface de jogo ou acionar lógica de game over.
 */
public class HealthComponent extends Component {

    private int currentHealth;
    private int maxHealth;

    /**
     * Construtor do componente de vida.
     * @param maxHealth Quantidade máxima de vida
     */
    public HealthComponent(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }

    /**
     * Devolve a vida atual da entidade.
     * @return Número de corações/vida disponíveis
     */
    public int getCurrentHealth() {
        return currentHealth;
    }

    /**
     * Devolve o valor máximo de vida da entidade.
     * @return Vida máxima
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * Aumenta a vida atual em 1 unidade (até ao máximo).
     */
    public void increaseHealth() {
        if (currentHealth < maxHealth) {
            currentHealth++;
        }
    }

    /**
     * Reduz a vida atual em 1 unidade (mínimo 0).
     */
    public void decreaseHealth() {
        if (currentHealth > 0) {
            currentHealth--;
        }
    }

    /**
     * Define a vida máxima e reseta a vida atual para esse valor.
     * @param max Novo valor máximo de vida
     */
    public void setMaxHealth(int max) {
        this.maxHealth = max;
        this.currentHealth = max;
    }
}
