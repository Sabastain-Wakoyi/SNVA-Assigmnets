//#include <stdio.h>
//        #include <stdlib.h>
//        #include <pthread.h>
//        #include <semaphore.h>
//        #include <time.h>
//        #include "buffer.h"
//
//        buffer_item buffer[BUFFER_SIZE];
//        int in = 0, out = 0;
//
//        pthread_mutex_t mutex;
//        sem_t empty, full;
//
//        void *producer(void *param);
//        void *consumer(void *param);
//
//        int insert_item(buffer_item item) {
//        if ((in + 1) % BUFFER_SIZE == out) {
//        return -1; // buffer is full
//        } else {
//        buffer[in] = item;
//        in = (in + 1) % BUFFER_SIZE;
//        return 0;
//        }
//        }
//
//        int remove_item(buffer_item *item) {
//        if (in == out) {
//        return -1; // buffer is empty
//        } else {
//        *item = buffer[out];
//        out = (out + 1) % BUFFER_SIZE;
//        return 0;
//        }
//        }
//
//        int main(int argc, char *argv[]) {
//        int sleepTime, numProducers, numConsumers;
//        srand(time(NULL)); // initialize random seed
//
//        if (argc != 4) {
//        fprintf(stderr, "Usage: %s <sleep time> <# of producer threads> <# of consumer threads>\n", argv[0]);
//        return -1;
//        }
//
//        sleepTime = atoi(argv[1]);
//        numProducers = atoi(argv[2]);
//        numConsumers = atoi(argv[3]);
//
//        pthread_t producers[numProducers];
//        pthread_t consumers[numConsumers];
//
//        pthread_mutex_init(&mutex, NULL);
//        sem_init(&empty, 0, BUFFER_SIZE);
//        sem_init(&full, 0, 0);
//
//        int i;
//        for (i = 0; i < numProducers; i++) {
//        pthread_create(&producers[i], NULL, producer, NULL);
//        }
//
//        for (i = 0; i < numConsumers; i++) {
//        pthread_create(&consumers[i], NULL, consumer, NULL);
//        }
//
//        sleep(sleepTime);
//
//        return 0;
//        }
//
//        void *producer(void *param) {
//        buffer_item item;
//
//        while (1) {
//        int sleepTime = rand() % 5 + 1; // sleep for random period of time
//        sleep(sleepTime);
//
//        item = rand() % 1000; // produce random item
//
//        sem_wait(&empty);
//        pthread_mutex_lock(&mutex);
//
//        if (insert_item(item) == -1) {
//        fprintf(stderr, "Producer error: buffer full\n");
//        } else {
//        printf("Producer produced %d\n", item);
//        }
//
//        pthread_mutex_unlock(&mutex);
//        sem_post(&full);
//        }
//        }
//
//        void *consumer(void *param) {
//        buffer_item item;
//
//        while (1) {
//        int sleepTime = rand() % 5 + 1; // sleep for random period of time
//        sleep(sleepTime);
//
//        sem_wait(&full);
//        pthread_mutex_lock(&mutex);
//
//        if (remove_item(&item) == -1) {
//        fprintf(stderr, "Consumer error: buffer empty\n");
//        } else {
//        printf("Consumer consumed %d\n", item);
//        }
//
//        pthread_mutex_unlock(&mutex);
//        sem_post(&empty);
//        }
//        }
