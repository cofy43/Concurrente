import matplotlib.pyplot as plt

threads = [2,3,7,15,21,30,50]
timeTAS = [4,6,21,25,40,44,55]
timeTTAS = [1,2,5,9,12,15,28]
timeBackoff = [5,4,5,6,6,7,13]
timeCLH = [3,3,10,1500,2400,2980,4560]
timeMCL = [22,35,256,3900,4800,7200,92520]

plt.plot(threads, timeTAS, label="TAS")
plt.plot(threads, timeTTAS, label="TTAS")
plt.plot(threads, timeBackoff, label="Backoff")
#plt.plot(threads, timeCLH, label="CLHL")
#plt.plot(threads, timeMCL, label="MCL")
plt.legend()
plt.show()
