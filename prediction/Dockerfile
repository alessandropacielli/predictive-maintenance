FROM python:3.7.7

WORKDIR /usr/src/prediction

COPY ./requirements.txt ./
RUN apt-get install gcc make git
RUN pip install -r requirements.txt

COPY . .


CMD [ "faust", "-A",  "app", "worker", "-l", "info" ]
