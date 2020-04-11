import argparse
import configparser

def main():
  # Arguments
  parser = argparse.ArgumentParser()
  parser.add_argument('--config', type=str, default='/etc/forecast/forecast.yml', help='configuration file for the forecasting service')  
  args = vars(parser.parse_args())

  # Read and parse config file
  

if __name__ == '__main__':
  main()