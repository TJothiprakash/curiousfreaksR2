package designpatterns.abstract_factory;

import designpatterns.factory.Transportation;

import javax.swing.text.TabableView;

public class RuralSeaTransport implements Transportation {
    @Override
    public void modeOfTransport() {
        System.out.println("Rural Sea Transport");
    }
}
