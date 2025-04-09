package edu.itau.vaga.dtos;

import org.springframework.stereotype.Component;

@Component
public class Estatisticas {
    private int count;
    private double sum;
    private double avg;
    private double min;
    private double max;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public Estatisticas() {
        this.count = 0;
        this.sum = 0.0;
        this.avg = 0.0;
        this.min = 0.0;
        this.max = 0.0;
    }

}