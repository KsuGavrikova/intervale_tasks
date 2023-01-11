package by.intervale.supportService.service;

import by.intervale.supportService.model.SupportRequest;
import by.intervale.supportService.model.helper.Adviser;
import by.intervale.supportService.model.helper.Editor;
import by.intervale.supportService.model.helper.Helper;
import by.intervale.supportService.model.helper.Technic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


class SupportServiceIfElseTest {
    @InjectMocks
    SupportServiceIfElse supportServiceIfElse;

    @Mock
    Editor editor;
    @Mock
    Technic technic;
    @Mock
    Adviser adviser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Mockito.doNothing().when(editor).help(Mockito.any());
        Mockito.doNothing().when(technic).help(Mockito.any());
        Mockito.doNothing().when(adviser).help(Mockito.any());
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(adviser, editor, technic);
    }

    @Test
    void testProcessRequestEditor() {
        //given
        SupportRequest supportRequest = new SupportRequest("Any problem", Helper.SITE);
        //when
        supportServiceIfElse.processRequest(supportRequest);
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
        supportServiceIfElse.processRequest(supportRequest);
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
        supportServiceIfElse.processRequest(supportRequest);
        //then
        Mockito.verify(adviser, Mockito.times(1)).help(Mockito.any());
        Mockito.verify(technic, Mockito.times(0)).help(Mockito.any());
        Mockito.verify(editor, Mockito.times(0)).help(Mockito.any());
    }

}