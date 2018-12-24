import Carousel from 'nuka-carousel';
import React, { PureComponent } from 'react';
import styled from 'styled-components';

import { Map } from './Map';

const FLOORS = [
  'b6',
  'b5',
  'b4',
  'b3',
  'b2',
  'b1',
  '1',
  '2',
  '3',
  '4',
  '5',
  '6',
  '7',
  '8',
  '9',
  '10',
  '11',
  '12',
];

class MapCarouselComponent extends PureComponent {
  render () {
    return (
      <Carousel className={this.props.className} slideIndex={6}>
        {FLOORS.map((floor) => <Map key={`map-${floor}`} name={floor} />)}
      </Carousel>
    );
  }
}

export const MapCarousel = styled(MapCarouselComponent)`
  flex: 1;
`;
