# Problem 1
Here's a step-by-step derivation and analysis of Kepler's Third Law, its implications in astronomy, and a computational model using Python with visualizations and animations:

### 1. Derivation of Kepler's Third Law:

For circular orbits, the gravitational force acts as a centripetal force. Thus:

$F_\text{gravity} = F_\text{centripetal}$

Using Newton’s law of universal gravitation:

$\frac{GMm}{r^2} = \frac{mv^2}{r}$

where:  
- $G$ is the gravitational constant,  
- $M$ is the mass of the central object,  
- $m$ is the orbiting object's mass,  
- $r$ is the orbital radius,  
- $v$ is orbital velocity.

Simplifying:

$\frac{GM}{r^2} = \frac{v^2}{r} \quad \Rightarrow \quad v^2 = \frac{GM}{r}$

Orbital period $T$ is related to velocity by $v = \frac{2\pi r}{T}$, thus:

$\left(\frac{2\pi r}{T}\right)^2 = \frac{GM}{r}$

Solve for $T^2$:

$T^2 = \frac{4\pi^2}{GM} r^3$

This is Kepler's Third Law, which states:

$\boxed{T^2 \propto r^3}$

The orbital period squared is proportional to the orbital radius cubed.

---

### 2. Implications for Astronomy:

Kepler’s Third Law has significant implications:

- **Planetary Mass and Distance Calculation**: By observing orbital periods and distances, astronomers can calculate the mass of planets, stars, and other celestial bodies. It’s crucial for understanding star systems and galaxies.

- **Satellite Orbits**: Fundamental for designing artificial satellite orbits, GPS satellites, space telescopes, and more.

---

### 3. Real-world Examples:

- **Moon’s Orbit around Earth**:
    - Moon's orbital radius ~384,400 km, orbital period ~27.3 days.
    - Using Kepler’s law, we can verify Earth’s mass accurately.

- **Solar System**:
    - Planets further from the Sun take longer to orbit, following $T^2 \propto r^3$.
    - Examples: Mercury (~88 days), Earth (1 year), Jupiter (~12 years), Saturn (~29 years).

---

### 4. Python Computational Model:

Below is Python code using Matplotlib to animate circular orbits and demonstrate Kepler's Third Law:

### Python Implementation:
```python
import numpy as np
import matplotlib.pyplot as plt
from matplotlib.animation import FuncAnimation

# Constants
G = 6.67430e-11  # Gravitational constant
M = 5.972e24     # Mass of Earth in kg

# Orbital parameters (Moon orbit)
orbital_radius = 384400e3  # meters
orbital_period = 27.3 * 24 * 3600  # seconds (Moon orbital period)

# Verify Kepler's third law calculation
computed_T_squared = (4 * np.pi**2 * orbital_radius**3) / (G * M)
computed_period = np.sqrt(computed_T_squared)
computed_period_days = computed_period / (24 * 3600)

# Figure setup
fig, ax = plt.subplots(figsize=(7, 7))
ax.set_xlim(-1.2 * orbital_radius, 1.2 * orbital_radius)
ax.set_ylim(-1.2 * orbital_radius, 1.2 * orbital_radius)
ax.set_aspect('equal')
ax.set_title('Moon Orbiting Earth (Circular Orbit Animation)', fontsize=14)

# Plot Earth
ax.plot(0, 0, 'bo', markersize=20, label='Earth')

# Plot orbital path (static)
theta = np.linspace(0, 2*np.pi, 300)
orbit_path_x = orbital_radius * np.cos(theta)
orbit_path_y = orbital_radius * np.sin(theta)
ax.plot(orbit_path_x, orbit_path_y, 'gray', linestyle='--', linewidth=1)

# Initialize Moon position as a Line2D object
moon, = ax.plot([], [], 'ro', markersize=8, label='Moon')

# Text for displaying the period
period_text = ax.text(0.02, 0.95, '', transform=ax.transAxes)

# Animation update function
def update(frame):
    angle = 2 * np.pi * frame / frames_per_orbit
    x = orbital_radius * np.cos(angle)
    y = orbital_radius * np.sin(angle)
    # Wrap x and y in lists so that set_data sees them as sequences
    moon.set_data([x], [y])
    period_text.set_text(
        f'Orbital Period: {computed_period_days:.2f} days\nFrame: {frame}/{frames_per_orbit}'
    )
    return moon, period_text

frames_per_orbit = 200
ani = FuncAnimation(fig, update, frames=frames_per_orbit, interval=50, blit=True)

ax.legend()
plt.show()
import numpy as np
import matplotlib.pyplot as plt
from matplotlib.animation import FuncAnimation

# Constants
G = 6.67430e-11  # Gravitational constant
M = 5.972e24     # Mass of Earth in kg

# Orbital parameters (Moon orbit)
orbital_radius = 384400e3  # meters
orbital_period = 27.3 * 24 * 3600  # seconds (Moon orbital period)

# Verify Kepler's third law calculation
computed_T_squared = (4 * np.pi**2 * orbital_radius**3) / (G * M)
computed_period = np.sqrt(computed_T_squared)
computed_period_days = computed_period / (24 * 3600)

# Figure setup
fig, ax = plt.subplots(figsize=(7, 7))
ax.set_xlim(-1.2 * orbital_radius, 1.2 * orbital_radius)
ax.set_ylim(-1.2 * orbital_radius, 1.2 * orbital_radius)
ax.set_aspect('equal')
ax.set_title('Moon Orbiting Earth (Circular Orbit Animation)', fontsize=14)

# Plot Earth
ax.plot(0, 0, 'bo', markersize=20, label='Earth')

# Plot orbital path (static)
theta = np.linspace(0, 2*np.pi, 300)
orbit_path_x = orbital_radius * np.cos(theta)
orbit_path_y = orbital_radius * np.sin(theta)
ax.plot(orbit_path_x, orbit_path_y, 'gray', linestyle='--', linewidth=1)

# Initialize Moon position as a Line2D object
moon, = ax.plot([], [], 'ro', markersize=8, label='Moon')

# Text for displaying the period
period_text = ax.text(0.02, 0.95, '', transform=ax.transAxes)

# Animation update function
def update(frame):
    angle = 2 * np.pi * frame / frames_per_orbit
    x = orbital_radius * np.cos(angle)
    y = orbital_radius * np.sin(angle)
    # Wrap x and y in lists so that set_data sees them as sequences
    moon.set_data([x], [y])
    period_text.set_text(
        f'Orbital Period: {computed_period_days:.2f} days\nFrame: {frame}/{frames_per_orbit}'
    )
    return moon, period_text

frames_per_orbit = 200
ani = FuncAnimation(fig, update, frames=frames_per_orbit, interval=50, blit=True)

ax.legend()
plt.show()

```

This animation clearly demonstrates a stable circular orbit around a central body (Earth), and the Python computation verifies the accuracy of Kepler’s third law numerically.

---

### 5. Explanation of the Simulation Results:

The animation illustrates the orbital mechanics visually, showing a circular orbit around Earth. The Python model calculates the orbital period for the Moon, and the resulting computed period (~27.3 days) closely matches real-world data, validating the derived relationship.

---

### Conclusion:

Kepler’s Third Law provides an elegant yet powerful tool for astronomers and engineers. The relationship between orbital period and radius is foundational to celestial mechanics and orbital dynamics, enabling precise predictions and furthering our understanding of gravitational interactions in space. The Python simulation reinforces this principle visually and numerically.