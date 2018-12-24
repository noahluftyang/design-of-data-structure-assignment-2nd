import React from 'react';

import { Button, ButtonWrapper } from './styles';
import { SearchInput } from '../SearchInput';
import { ReactComponent as Elevator } from '../../images/elevator.svg';
import { ReactComponent as Stairs } from '../../images/stairs.svg';

export const SearchComponent = ({
  className,
  endFloor,
  endPlace,
  onChange,
  onClick,
  startFloor,
  startPlace,
  way,
}) => (
  <section className={className}>
    {/* <ButtonWrapper>
        <Button active={way === 'elevator'} value="elevator" onClick={onClick}>
          <Elevator />
        </Button>
        <Button active={way === 'stairs'} value="stairs" onClick={onClick}>
          <Stairs />
        </Button>
      </ButtonWrapper> */}
    <SearchInput
      floor={startFloor}
      name="start"
      onChange={onChange}
      placeholder="출발지 선택"
      title="출발"
      type="text"
      value={startPlace}
    />
    <SearchInput
      floor={endFloor}
      name="end"
      onChange={onChange}
      placeholder="도착지 선택"
      title="도착"
      type="text"
      value={endPlace}
    />
  </section>
);
