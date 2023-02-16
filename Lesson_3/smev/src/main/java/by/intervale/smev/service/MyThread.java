package by.intervale.smev.service;

import by.intervale.smev.model.RequestInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * c.	Реализовать Worker который будет запускаться в отдельном потоке и эмулировать взаимодействие с ГИСГМП
 * i.	Вычитывать сообщения из персистентной очереди запросов на получение информации
 * ii.	Эмулировать взаимодействие с ГИСГМП
 * iii.	Помещать ответ в персистентную очередь ответов
 * iv.	Удалять запрос из очереди запросов
 **/

@Service
@RequiredArgsConstructor
public class MyThread extends Thread {
    private final Worker worker;

    @Override
    public void run() {
        System.out.println("run method");
        List<RequestInfo> messageList = worker.getRequestFromQueue();
        for (RequestInfo r : messageList) {
            System.out.println(" Request info " + r);
        }

//        iii.	Помещать ответ в персистентную очередь ответов
        worker.emulator(messageList);
    }

}
