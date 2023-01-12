package by.intervale.supportService.service;

import by.intervale.supportService.model.SupportRequest;
import by.intervale.supportService.model.helper.Adviser;
import by.intervale.supportService.model.helper.Editor;
import by.intervale.supportService.model.helper.Helper;
import by.intervale.supportService.model.helper.Technic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

class SupportServiceMapTest {

    SupportServiceMap supportService;

    @Spy
    Editor editor;
    @Spy
    Technic technic;
    @Spy
    Adviser adviser;

    List<Helper> helpers = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        helpers.add(editor);
        helpers.add(technic);
        helpers.add(adviser);

        supportService = new SupportServiceMap(helpers);

        Mockito.doNothing().when(editor).help(Mockito.any());
        Mockito.doNothing().when(technic).help(Mockito.any());
        Mockito.doNothing().when(adviser).help(Mockito.any());
    }

    @AfterEach
    void tearDown() {
        helpers.clear();
        Mockito.reset(adviser, editor, technic);
    }

    @Test
    void testProcessRequestEditor() {
        //given
        SupportRequest supportRequest = new SupportRequest("Any problem", Helper.SITE);
        //when
        supportService.processRequest(supportRequest);
        //then
        Mockito.verify(adviser, Mockito.times(0)).help(Mockito.any());
        Mockito.verify(technic, Mockito.times(0)).help(Mockito.any());
        Mockito.verify(editor, Mockito.times(1)).help(Mockito.any());
    }

    @Test
    void testProcessRequestTechnic() {
        //given
        SupportRequest supportRequest = new SupportRequest("Any problem", Helper.TECHNICAL);
        //when
        supportService.processRequest(supportRequest);
        //then
        Mockito.verify(adviser, Mockito.times(0)).help(Mockito.any());
        Mockito.verify(technic, Mockito.times(1)).help(Mockito.any());
        Mockito.verify(editor, Mockito.times(0)).help(Mockito.any());
    }

    @Test
    void testProcessRequestAdviser() {
        //given
        SupportRequest supportRequest = new SupportRequest("", "");
        //when
        supportService.processRequest(supportRequest);
        //then
        Mockito.verify(technic, Mockito.times(0)).help(Mockito.any());
        Mockito.verify(editor, Mockito.times(0)).help(Mockito.any());
    }

}
