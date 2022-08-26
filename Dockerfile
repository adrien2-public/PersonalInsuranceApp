FROM rabbitmq

# Define environment variables.
ENV RABBITMQ_USER user
ENV RABBITMQ_PASSWORD user


ADD init.sh /init.sh
RUN chmod +x /init.sh
EXPOSE 15672

# Define default command
CMD [" apt-get install sed", sed -i -e 's/\r$//' , "/init.sh"]
