import React from 'react';

const SPINNER_SIZES = {
  large: 70,
  medium: 50,
  small: 30,
};

const STROKE_WIDTHS = {
  small: 4,
  medium: 5,
  large: 6,
};

const PATH_CLASS_NAMES = {
  small: 'SmallSpinnerPath',
  medium: 'MediumSpinnerPath',
  large: 'LargeSpinnerPath',
};

export const Spinner = ({ size = 'small', ...props }) => {
  const baseSize = SPINNER_SIZES[size];
  const pathSize = baseSize / 2;
  const strokeWidth = STROKE_WIDTHS[size];
  const pathRadius = `${baseSize / 2 - strokeWidth}px`;
  const className = PATH_CLASS_NAMES[size];
  const containerClassName = `SpinnerContainer SpinnerContainer-${size} ${props.className}`;

  return (
    <div className={containerClassName}>
      <svg
        className={className}
        width={baseSize}
        height={baseSize}
        viewBox={`0 0 ${baseSize} ${baseSize}`}
      >
        <circle
          className="SpinnerPath"
          fill="none"
          stroke="currentColor"
          strokeWidth={strokeWidth}
          strokeLinecap="round"
          cx={pathSize}
          cy={pathSize}
          r={pathRadius}
        />
      </svg>
    </div>
  );
};
