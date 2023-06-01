package com.example.hao_activity_submission.Model;

import java.util.List;

public class IngredientsModel {
    private List<Ingredient> malt;
    private List<Hop> hops;
    private String yeast;

    public List<Ingredient> getMalt() {
        return malt;
    }

    public List<Hop> getHops() {
        return hops;
    }

    public String getYeast() {
        return yeast;
    }

    public class Ingredient {
        private String name;
        private QuantityModel amount;

        public String getName() {
            return name;
        }

        public QuantityModel getAmount() {
            return amount;
        }
    }

    public class Hop extends Ingredient {
        private String add;
        private String attribute;

        public String getAdd() {
            return add;
        }

        public String getAttribute() {
            return attribute;
        }
    }
}
