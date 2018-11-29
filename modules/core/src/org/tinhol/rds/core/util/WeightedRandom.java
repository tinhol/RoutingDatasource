package org.tinhol.rds.core.util;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.tuple.MutableTriple;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.List;

public class WeightedRandom {

    //value, weight, inclusiveEnd
    private List<MutableTriple<Integer, Integer, Integer>> values = new ArrayList<>();
    private Integer totalWeight = 0;

    public void addValue(Integer value, Integer weight) {
        values.add(MutableTriple.of(value, weight, values.stream().mapToInt(Triple::getMiddle).sum() + weight));
        totalWeight = values.stream().mapToInt(Triple::getMiddle).sum();
    }

    public List<MutableTriple<Integer, Integer, Integer>> getValues() {
        return values;
    }

    public void setValueWeightAndRecalc(Integer value, Integer weight) {
        values.stream().filter(t -> t.getLeft().equals(value)).forEach(t -> t.setMiddle(weight));
        recalc();
    }

    public void recalc() {
        Integer weight = 0;
        for(MutableTriple<Integer, Integer, Integer> triple : values) {
            weight += triple.getMiddle();
            triple.setRight(weight);
        }
        totalWeight = weight;
    }


    public void setValues(List<MutableTriple<Integer, Integer, Integer>> values) {
        this.values = values;
    }

    public Integer getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Integer totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Integer randomValue() {
        int random = RandomUtils.nextInt(1, totalWeight + 1);
        for (Triple<Integer, Integer, Integer> triple : values) {
            if (random <= triple.getRight()) {
                return triple.getLeft();
            }
        }

        return null;
    }
}
