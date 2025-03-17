import numpy as np
import matplotlib.pyplot as plt
from scipy.integrate import solve_ivp

# Define the forced damped pendulum equation
def pendulum_eq(t, y, b, g, A, omega):
    theta, omega_dot = y
    dtheta_dt = omega_dot
    domega_dt = -b * omega_dot - g * np.sin(theta) + A * np.cos(omega * t)
    return [dtheta_dt, domega_dt]

# Parameters
b = 0.2  # Damping coefficient
g = 9.81  # Gravitational constant (for L=1)
A = 1.2  # Driving force amplitude
omega = 2.0  # Driving frequency

# Initial conditions
t_span = (0, 50)  # Time span for simulation
y0 = [0.1, 0]  # Initial angle and angular velocity
t_eval = np.linspace(0, 50, 1000)

# Solve using Runge-Kutta method
sol = solve_ivp(pendulum_eq, t_span, y0, t_eval=t_eval, args=(b, g, A, omega), dense_output=True)

# Extract results
t = sol.t
theta = sol.y[0]
omega_vals = sol.y[1]

# Plot the angle vs. time
plt.figure(figsize=(10, 5))
plt.plot(t, theta, label='θ(t)')
plt.xlabel('Time (s)')
plt.ylabel('Angle (rad)')
plt.title('Forced Damped Pendulum Motion')
plt.legend()
plt.grid()
plt.show()

# Phase space plot
plt.figure(figsize=(6, 6))
plt.plot(theta, omega_vals, label='Phase Space')
plt.xlabel('θ (rad)')
plt.ylabel('ω (rad/s)')
plt.title('Phase Space Plot')
plt.legend()
plt.grid()
plt.show()

# Poincaré Section
poincare_t = np.arange(0, 50, 2*np.pi/omega)  # Stroboscopic map
poincare_points = sol.sol(poincare_t)

plt.figure(figsize=(6, 6))
plt.scatter(poincare_points[0], poincare_points[1], color='red', label='Poincaré Section')
plt.xlabel('θ (rad)')
plt.ylabel('ω (rad/s)')
plt.title('Poincaré Section')
plt.legend()
plt.grid()
plt.show()
