package Sprint0.UI;

import Sprint0.Controller.US04Controller;
import Sprint0.Model.GeographicArea;
import Sprint0.Model.GeographicAreaList;
import Sprint0.Model.TypeArea;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * US 04:
 * Como Administrador de Sistema pretendo saber
 * quais são as áreas geográficas de um determinado tipo.
 * */

public class US04UI {
    private String action;
    private boolean active;
    private TypeArea mTypeArea;
    private US04Controller ctrl04;

    public US04UI() {
        active = false;
    }

    public void run(GeographicAreaList list) {
        this.active = true;
        while(this.active) {
            getInput();
            update(list);
            display();
        }
    }

    private void getInput() {
        System.out.println("Please insert Geographic Area type:");
        Scanner input = new Scanner(System.in);
        this.action = input.nextLine();
        System.out.println("You entered Geographic Area Type: " + action);
    }

    private void update(GeographicAreaList list) {
        US04Controller ctrl04 = new US04Controller(list);
        ctrl04.setGeographicAreaListWithGeographicAreasFromTypeGivenUS04UI(this.action);
    }

    private void display() {
        GeographicAreaList gal = ctrl04.getGeographicAreaList();
        List<GeographicArea> list = new ArrayList<>();
        String result = list.stream().map(GeographicArea::getName).collect(Collectors.joining(", "));
        System.out.print(result);
    }
}
