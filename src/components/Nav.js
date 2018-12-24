import { Link } from '@reach/router';
import React from 'react';
import styled from 'styled-components';

import { ReactComponent as Arrow } from '../images/arrow.svg';
import { ReactComponent as Back } from '../images/back.svg';

export const NavComponent = ({ className, endPlace, startPlace }) => (
  <nav className={className}>
    <Link to="..">
      <Back />
    </Link>
    <h3>{startPlace}</h3>
    <Arrow />
    <h3>{endPlace}</h3>
  </nav>
);

export const Nav = styled(NavComponent)`
  align-items: center;
  background-color: #3a94fb;
  color: #fff;
  display: flex;
  justify-content: center;
  padding: 1rem;

  & a {
    display: flex;
    position: absolute;
    left: 1rem;
  }

  & svg {
    margin: 0.25rem;
  }
`;
