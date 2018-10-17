package com.vaadin.flow.component.incubator.vaadincom;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.incubator.Popup;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.demo.DemoView;
import com.vaadin.flow.router.Route;

import java.util.concurrent.ThreadLocalRandom;

@Route("popup")
public class PopupView extends DemoView {

    @Override
    protected void initView() {
        addBasicExample();
        addCloseOnClickExample();
        addOpenedExample();
        addShowHideExample();
        addUnbindExample();
    }

    private void addBasicExample() {
        Button button = new Button("Push Me");
        button.setId("push-me");

        Popup popup = new Popup();
        popup.setFor(button.getId().orElse(null));
        Div text = new Div();
        text.setText("element 1");
        Div text2 = new Div();
        text2.setText("element 2");
        popup.add(text, text2);

        Div closeOnClickStatus = new Div();
        closeOnClickStatus.setText("Close on click: " + popup.isCloseOnClick());

        addCard("Basic popup usage", button, popup, closeOnClickStatus);
    }

    private void addCloseOnClickExample() {
        Button button = new Button("Push Me");
        button.setId("push-me");

        Popup popup = new Popup();
        popup.setFor(button.getId().orElse(null));
        popup.setCloseOnClick(true);
        Div text = new Div();
        text.setText("element 1");
        Div text2 = new Div();
        text2.setText("element 2");
        popup.add(text, text2);

        Div closeOnClickStatus = new Div();
        closeOnClickStatus.setText("Close on click: " + popup.isCloseOnClick());

        addCard("Popup with close on popup usage", button, popup, closeOnClickStatus);
    }

    private void addOpenedExample() {
        Div target = new Div();
        target.setText("I have popup, click me. P.S: pop-up will gone with first click anywhere");
        target.setId("div-push-me");

        Popup popup = new Popup();
        popup.setOpened(true);
        popup.setFor(target.getId().orElse(null));

        popup.add(new Icon(VaadinIcon.VAADIN_H), new Icon(VaadinIcon.VAADIN_H), new Icon(VaadinIcon.VAADIN_H));

        addCard("Opened popup usage", target, popup);
    }

    private void addShowHideExample() {
        Div target = new Div();
        target.setText("I have popup, click me(I dont change popup content)");
        target.setId("div-push-me-2");

        Popup popup = new Popup();
        popup.setFor(target.getId().orElse(null));
        popup.add(new Icon(VaadinIcon.VAADIN_H), new Icon(VaadinIcon.VAADIN_H), new Icon(VaadinIcon.VAADIN_H));

        Button button = new Button("Change popup content");
        button.addClickListener(e -> {
            if (popup.isOpened()) {
                popup.hide();
            } else {
                int elCount = ThreadLocalRandom.current().nextInt(1, 10);
                popup.removeAll();
                for (int i = 0 ; i < elCount; i++) {
                    popup.add(new Icon(VaadinIcon.VAADIN_H));
                }
                popup.show();
            }
        });

        HorizontalLayout hl = new HorizontalLayout();
        hl.add(target, popup, button);

        addCard("Show/Hide popup usage.", hl);
    }

    private void addUnbindExample() {
        Div target = new Div();
        target.setText("I have popup, click me");
        target.setId("div-push-me-2");

        Popup popup = new Popup();
        popup.setFor(target.getId().orElse(null));
        Icon orange = new Icon(VaadinIcon.VAADIN_H);
        orange.setColor("orange");
        popup.add(new Icon(VaadinIcon.VAADIN_H), orange, new Icon(VaadinIcon.VAADIN_H));

        Button button = new Button("Bind/Unbind vaadin");
        button.addClickListener(e -> {
            if (popup.getFor() != null) {
                popup.setFor(null);
            } else {
                popup.setFor(target.getId().orElse(null));
            }
        });

        HorizontalLayout hl = new HorizontalLayout();
        hl.add(target, popup, button);

        addCard("Bind/Unbind popup to target usage.", hl);
    }
}