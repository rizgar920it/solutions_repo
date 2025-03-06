# Problem 1


## 1. Theoretical Foundation

Projectile motion is a classic application of Newton’s laws in two dimensions. Let’s derive the governing equations from first principles, assuming no air resistance and a constant gravitational field, to establish the framework.

### Derivation of the Equations of Motion

Consider a projectile launched from the origin $(x_0, y_0) = (0, 0)$ with an initial velocity $v_0$ at an angle $\theta$ above the horizontal. The initial velocity components are:

- Horizontal: $v_{x0} = v_0 \cos\theta$
- Vertical: $v_{y0} = v_0 \sin\theta$

The only acceleration is due to gravity, acting downward: $a_y = -g$, where $g \approx 9.8 \, \text{m/s}^2$, and $a_x = 0$. Starting with the kinematic equations:

#### Horizontal Motion
- Acceleration: $\frac{d^2 x}{dt^2} = 0$
- Velocity: $\frac{dx}{dt} = v_{x0} = v_0 \cos\theta$
- Position: $x(t) = v_0 \cos\theta \cdot t$

#### Vertical Motion
- Acceleration: $\frac{d^2 y}{dt^2} = -g$
- Velocity: $\frac{dy}{dt} = v_{y0} - g t = v_0 \sin\theta - g t$
- Position: Integrate velocity: $y(t) = v_0 \sin\theta \cdot t - \frac{1}{2} g t^2$

These are the parametric equations of motion, forming a parabola under ideal conditions.

### Family of Solutions
The solutions depend on free parameters: $v_0$, $\theta$, and $g$. Additionally, if launched from a height $h$ (i.e., $y_0 = h$), the vertical position becomes:

$$y(t) = h + v_0 \sin\theta \cdot t - \frac{1}{2} g t^2$$

Varying \(v_0\), \(\theta\), \(g\), or \(h\) generates a family of trajectories, from shallow arcs to steep climbs, adaptable to diverse scenarios.

## 2. Analysis of the Range

The range \(R\) is the horizontal distance traveled when the projectile returns to \(y = 0\). For launch height \(h = 0\):

### Time of Flight
Set $y(t) = 0$:
$$ 0 = v_0 \sin\theta \cdot t - \frac{1}{2} g t^2 $$
$$ t (v_0 \sin\theta - \frac{1}{2} g t) = 0 $$
- Trivial solution: $t = 0s$ (start)
- Non-trivial: $(t = \frac{2 v_0 \sin\theta}{g})$
### Range Equation
Substitute into \(x(t)\):
$$ R = v_0 \cos\theta \cdot \frac{2 v_0 \sin\theta}{g} = \frac{2 v_0^2 \sin\theta \cos\theta}{g} $$
Using the identity $$2 \sin\theta \cos\theta = \sin 2\theta$$:
$$ R = \frac{v_0^2 \sin 2\theta}{g} $$

### Dependence on Angle
- **Maximum Range**: $R$ is maximized when $\sin 2\theta = 1$, so $2\theta = 90^\circ$, $\theta = 45^\circ$. Then, $R_{\text{max}} = \frac{v_0^2}{g}$.
- **Symmetry**: $R(\theta) = R(90^\circ - \theta)$, e.g., $15^\circ$ and $75^\circ$ yield the same range.
- **Limits**: At $\theta = 0^\circ$ or $90^\circ$, $\sin 2\theta = 0$, so $R = 0$.

### Influence of Parameters
- **Initial Velocity ($v_0$)**: $R \propto v_0^2$, a quadratic increase.
- **Gravity ($g$)**: $R \propto \frac{1}{g}$, inversely proportional. On the Moon ($g \approx 1.62 \, \text{m/s}^2$), range is ~6 times greater than Earth’s.
- **Launch Height ($h > 0$)**: Increases time of flight, thus extending range. Requires solving a quadratic for $t$, complicating the expression.

## 3. Practical Applications

This model applies to:
- **Sports**: Optimizing a basketball shot or golf drive ($\theta \approx 45^\circ$ for flat ground).
- **Engineering**: Artillery or rocket launches, adjusting for terrain or wind.
- **Astrophysics**: Simplified trajectories of celestial bodies (neglecting orbital mechanics).

For uneven terrain (landing at $y = h_f$), modify the range calculation. With air resistance, numerical methods (e.g., Euler or Runge-Kutta) are needed, reducing range and altering the optimal angle.

## 4. Implementation

Here’s a Python script to simulate and visualize the range versus angle:

```python
import numpy as np
import matplotlib.pyplot as plt

def projectile_range(v0, theta_deg, g=9.8, h=0):
    theta = np.radians(theta_deg)
    if h == 0:
        return (v0**2 * np.sin(2 * theta)) / g
    else:
        # Time of flight with initial height
        a = -g / 2
        b = v0 * np.sin(theta)
        c = h
        t = (-b + np.sqrt(b**2 - 4*a*c)) / (2*a)  # Positive root
        return v0 * np.cos(theta) * t

# Parameters
v0_values = [10, 20, 30]  # m/s
g = 9.8  # m/s^2
theta_deg = np.arange(0, 91, 1)
h_values = [0, 10]  # m

# Plotting
plt.figure(figsize=(10, 6))
for v0 in v0_values:
    R = [projectile_range(v0, t, g, h=0) for t in theta_deg]
    plt.plot(theta_deg, R, label=f'v0 = {v0} m/s, h = 0 m')
for h in h_values:
    R = [projectile_range(20, t, g, h) for t in theta_deg]
    plt.plot(theta_deg, R, '--', label=f'v0 = 20 m/s, h = {h} m')

plt.xlabel('Angle of Projection (degrees)')
plt.ylabel('Range (meters)')
plt.title('Range vs. Angle of Projection')
plt.legend()
plt.grid(True)
plt.show()
```

### Output
This generates a plot showing:
- Range peaking at $45^\circ$ for $h = 0$.
- Increased range with higher $v_0$.
- Extended range and shifted optimal angle $below $45^\circ$ with $h > 0$.

## Discussion

### Limitations
- **Idealization**: Assumes no air resistance, flat terrain, and constant $g$.
- **Realism**: Drag reduces range and shifts the optimal angle (e.g., ~30–40° for a golf ball).

### Extensions
- **Drag**: Incorporate $-k v$ terms, solved numerically.
- **Wind**: Add velocity components to $v_x$ and $v_y$.
- **Terrain**: Model $y = f(x)$ for landing height.

This framework, while simple, is a stepping stone to complex simulations in physics and engineering.

---

This delivers a theoretical foundation, analytical insights, a practical implementation, and a discussion of real-world adaptations. Let me know if you’d like to refine any section or explore extensions like air resistance numerically!