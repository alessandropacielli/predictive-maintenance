FROM python:3.7.7

WORKDIR /usr/src/prediction
COPY ./requirements.txt ./
RUN pip install -r requirements.txt

COPY ./prediction ./prediction/
COPY ./resources ./resources/
COPY app.py ./


CMD [ "faust", "-A",  "app", "worker", "-l", "info" ]
