package anz;

import java.util.*;

/**
 * ANZ Code Challenge
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<Integer> results = solution.filter(solution.initialTestData());
        System.out.println(results);
    }

    private List<Integer> filter(List<LifeSpan> lifeSpans) {
        Map<Integer, Integer> diedCountsOfYear = new HashMap<>();
        Map<Integer, Integer> bornCountsOfYear = new HashMap<>();

        calculateDiedAndBornNumbers(lifeSpans, diedCountsOfYear, bornCountsOfYear);
        List<Integer> results = getLowerYears(diedCountsOfYear, bornCountsOfYear);

        Collections.sort(results);
        return results;
    }

    /**
     * Get those years that population was lower than preceding year
     * <p>
     * Possible scenario:
     * 1. Some people died in a year and the next year no new born - Lower
     * 2. Some people died in a year, next year there were some new borns but the number of new borns is less than the number of people died in last year - Lower
     *
     * @param diedCountsOfYear
     * @param bornCountsOfYear
     * @return
     */
    private List<Integer> getLowerYears(Map<Integer, Integer> diedCountsOfYear, Map<Integer, Integer> bornCountsOfYear) {
        List<Integer> results = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : diedCountsOfYear.entrySet()) {
            if (bornCountsOfYear.containsKey(entry.getKey() + 1)) {
                Integer result = bornCountsOfYear.get(entry.getKey() + 1) - entry.getValue();
                if (result < 0) {
                    results.add(result);
                }
            } else {
                results.add(entry.getKey() + 1);
            }
        }
        return results;
    }

    /**
     * Get born and died counts each year from the life span list
     *
     * @param lifeSpans
     * @param diedCountsOfYear
     * @param bornCountsOfYear
     */
    private void calculateDiedAndBornNumbers(List<LifeSpan> lifeSpans, Map<Integer, Integer> diedCountsOfYear, Map<Integer, Integer> bornCountsOfYear) {
        for (LifeSpan lifeSpan : lifeSpans) {
            if (lifeSpan.getDiedYear() != null) {
                calculateByYear(diedCountsOfYear, lifeSpan.getDiedYear());
            }
            calculateByYear(bornCountsOfYear, lifeSpan.getBornYear());
        }
    }

    private void calculateByYear(Map<Integer, Integer> map, Integer year) {
        if (map.containsKey(year)) {
            map.put(year, map.get(year) + 1);
        } else {
            map.put(year, 1);
        }
    }

    private List<LifeSpan> initialTestData() {
        List<LifeSpan> testData = new ArrayList<>();
        testData.add(new LifeSpan(1902, 1991));
        testData.add(new LifeSpan(1941, 1978));
        testData.add(new LifeSpan(2004, null));
        testData.add(new LifeSpan(1957, null));
        testData.add(new LifeSpan(1989, 2008));
        testData.add(new LifeSpan(1909, 2005));
        testData.add(new LifeSpan(1918, null));
        testData.add(new LifeSpan(1913, 2010));
        testData.add(new LifeSpan(1979, null));
        testData.add(new LifeSpan(1961, 2002));
        testData.add(new LifeSpan(1977, 2003));
        testData.add(new LifeSpan(1909, 1991));
        return testData;
    }

    class LifeSpan {
        private Integer bornYear;
        private Integer diedYear;

        LifeSpan(Integer bornYear, Integer diedYear) {
            this.bornYear = bornYear;
            this.diedYear = diedYear;
        }

        Integer getBornYear() {
            return bornYear;
        }

        Integer getDiedYear() {
            return diedYear;
        }
    }
}
